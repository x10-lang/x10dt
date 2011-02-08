#ifndef __X10_UTIL_BOX_H
#define __X10_UTIL_BOX_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_FUN_0_0_H_NODEPS
#include <x10/lang/Fun_0_0.h>
#undef X10_LANG_FUN_0_0_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 
class TempNoInline_3;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class Box;
template <> class Box<void>;
template<class FMGL(T)> class Box : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    FMGL(T) FMGL(value);
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::lang::Fun_0_0<FMGL(T)>::template itable<x10::util::Box<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::Box<FMGL(T)> > _itable_1;
    
    void _instance_init();
    
    void _constructor(FMGL(T) x);
    
    static x10aux::ref<x10::util::Box<FMGL(T)> > _make(FMGL(T) x);
    
    virtual FMGL(T) __apply();
    virtual x10_int hashCode();
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10_boolean equals(x10aux::ref<x10::lang::Any> x);
    FMGL(T) value();
    virtual x10aux::ref<x10::util::Box<FMGL(T)> > x10__util__Box____x10__util__Box__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Box<void> : public x10::lang::Object {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    template<class FMGL(T)> static x10aux::ref<x10::util::Box<FMGL(T)> >
      __implicit_convert(
      FMGL(T) x);
    
    
};

} } 
#endif // X10_UTIL_BOX_H

namespace x10 { namespace util { 
template<class FMGL(T)> class Box;
} } 

#ifndef X10_UTIL_BOX_H_NODEPS
#define X10_UTIL_BOX_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Int.h>
#include <x10/lang/String.h>
#include <x10/lang/Any.h>
#include <x10/lang/Boolean.h>
#include <x10/compiler/TempNoInline_3.h>
#ifndef X10_UTIL_BOX_H_GENERICS
#define X10_UTIL_BOX_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::util::Box<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::Box<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::Box<FMGL(T)> >(), 0, sizeof(x10::util::Box<FMGL(T)>))) x10::util::Box<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_BOX_H_GENERICS
#ifndef X10_UTIL_BOX_H_IMPLEMENTATION
#define X10_UTIL_BOX_H_IMPLEMENTATION
#include <x10/util/Box.h>



//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.PropertyDecl_c
template<class FMGL(T)> typename x10::lang::Fun_0_0<FMGL(T)>::template itable<x10::util::Box<FMGL(T)> >  x10::util::Box<FMGL(T)>::_itable_0(&x10::util::Box<FMGL(T)>::equals, &x10::util::Box<FMGL(T)>::hashCode, &x10::util::Box<FMGL(T)>::__apply, &x10::util::Box<FMGL(T)>::toString, &x10::util::Box<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::util::Box<FMGL(T)> >  x10::util::Box<FMGL(T)>::_itable_1(&x10::util::Box<FMGL(T)>::equals, &x10::util::Box<FMGL(T)>::hashCode, &x10::util::Box<FMGL(T)>::toString, &x10::util::Box<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::util::Box<FMGL(T)>::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::Box<FMGL(T)> >())};
template<class FMGL(T)> void x10::util::Box<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Box<FMGL(T)>");
    
}


//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::Box<FMGL(T)>::_constructor(FMGL(T) x)
{
    this->::x10::lang::Object::_constructor();
    {
        
        //#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.AssignPropertyCall_c
        FMGL(value) = x;
        {
         
        }
    }
    
}
template<class FMGL(T)>
x10aux::ref<x10::util::Box<FMGL(T)> > x10::util::Box<FMGL(T)>::_make(
  FMGL(T) x)
{
    x10aux::ref<x10::util::Box<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::Box<FMGL(T)> >(), 0, sizeof(x10::util::Box<FMGL(T)>))) x10::util::Box<FMGL(T)>();
    this_->_constructor(x);
    return this_;
}



//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Box<FMGL(T)>::__apply() {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Box<FMGL(T)> >)this)->FMGL(value);
    
}

//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::Box<FMGL(T)>::hashCode() {
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
    return x10aux::hash_code(((x10aux::ref<x10::util::Box<FMGL(T)> >)this)->
                               FMGL(value));
    
}

//#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::String> x10::util::Box<FMGL(T)>::toString(
  ) {
    
    //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
    return x10aux::to_string(((x10aux::ref<x10::util::Box<FMGL(T)> >)this)->
                               FMGL(value));
    
}

//#line 24 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::Box<FMGL(T)>::equals(
  x10aux::ref<x10::lang::Any> x) {
    
    //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(x, X10_NULL))) {
        
        //#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
        return false;
        
    }
    
    //#line 28 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10If_c
    if (x10aux::instanceof<FMGL(T)>(x)) {
        
        //#line 29 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10LocalDecl_c
        FMGL(T) y = x10aux::class_cast<FMGL(T)>(x);
        
        //#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
        return x10aux::equals(((x10aux::ref<x10::util::Box<FMGL(T)> >)this)->
                                FMGL(value),x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(y));
        
    }
    
    //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10If_c
    if (x10aux::instanceof<x10aux::ref<x10::util::Box<FMGL(T)> > >(x))
    {
        
        //#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10LocalDecl_c
        FMGL(T) y =
          x10aux::nullCheck((x10aux::class_cast<x10aux::ref<x10::util::Box<FMGL(T)> > >(x)))->
            FMGL(value);
        
        //#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
        return x10aux::equals(((x10aux::ref<x10::util::Box<FMGL(T)> >)this)->
                                FMGL(value),x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(y));
        
    }
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
    return false;
    
}

//#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10MethodDecl_c

//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Box<FMGL(T)>::value(
  ) {
    
    //#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Box<FMGL(T)> >)this)->
             FMGL(value);
    
}

//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::Box<FMGL(T)> >
  x10::util::Box<FMGL(T)>::x10__util__Box____x10__util__Box__this(
  ) {
    
    //#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Box<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::util::Box<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::Box<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::util::Box<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(value));
    
}

template<class FMGL(T)> void x10::util::Box<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(value) = buf.read<FMGL(T)>();
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::Box<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::Box<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Box<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::covariant};
    const char *baseName = "x10.util.Box";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
template<class FMGL(T)> x10aux::ref<x10::util::Box<FMGL(T)> >
  x10::util::Box<void>::__implicit_convert(FMGL(T) x)
{
    
    //#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Box.x10": x10.ast.X10Return_c
    return x10::util::Box<FMGL(T)>::_make(x);
    
}
#endif // X10_UTIL_BOX_H_IMPLEMENTATION
#endif // __X10_UTIL_BOX_H_NODEPS
