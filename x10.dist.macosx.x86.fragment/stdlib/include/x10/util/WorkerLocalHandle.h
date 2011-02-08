#ifndef __X10_UTIL_WORKERLOCALHANDLE_H
#define __X10_UTIL_WORKERLOCALHANDLE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_FUN_0_0_H_NODEPS
#include <x10/lang/Fun_0_0.h>
#undef X10_LANG_FUN_0_0_H_NODEPS
#define X10_LANG_VOIDFUN_0_1_H_NODEPS
#include <x10/lang/VoidFun_0_1.h>
#undef X10_LANG_VOIDFUN_0_1_H_NODEPS
#define X10_LANG_PLACELOCALHANDLE_STRUCT_H_NODEPS
#include <x10/lang/PlaceLocalHandle.struct_h>
#undef X10_LANG_PLACELOCALHANDLE_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class PlaceLocalHandle;
} } 
#include <x10/lang/PlaceLocalHandle.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class PlaceLocalHandle;
} } 
#include <x10/lang/PlaceLocalHandle.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
class Dist;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class WorkerLocalHandle;
template <> class WorkerLocalHandle<void>;
template<class FMGL(T)> class WorkerLocalHandle : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[4];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::lang::Fun_0_0<FMGL(T)>::template itable<x10::util::WorkerLocalHandle<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::WorkerLocalHandle<FMGL(T)> > _itable_1;
    
    static typename x10::lang::VoidFun_0_1<FMGL(T)>::template itable<x10::util::WorkerLocalHandle<FMGL(T)> > _itable_2;
    
    void _instance_init();
    
    x10::lang::PlaceLocalHandle<x10aux::ref<x10::array::Array<FMGL(T)> > >
      FMGL(store);
    
    void _constructor(FMGL(T) t);
    
    static x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> > _make(FMGL(T) t);
    
    virtual FMGL(T) __apply();
    virtual void __apply(FMGL(T) t);
    virtual x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> > x10__util__WorkerLocalHandle____x10__util__WorkerLocalHandle__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class WorkerLocalHandle<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_WORKERLOCALHANDLE_H

namespace x10 { namespace util { 
template<class FMGL(T)>
class WorkerLocalHandle;
} } 

#ifndef X10_UTIL_WORKERLOCALHANDLE_H_NODEPS
#define X10_UTIL_WORKERLOCALHANDLE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/VoidFun_0_1.h>
#include <x10/lang/PlaceLocalHandle.h>
#include <x10/array/Array.h>
#include <x10/lang/PlaceLocalHandle.h>
#include <x10/array/Array.h>
#include <x10/array/Dist.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/array/Array.h>
#include <x10/lang/Runtime.h>
#ifndef X10_UTIL_WORKERLOCALHANDLE__CLOSURE__0_CLOSURE
#define X10_UTIL_WORKERLOCALHANDLE__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_util_WorkerLocalHandle__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > >::template itable <x10_util_WorkerLocalHandle__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<x10::array::Array<FMGL(T)> > __apply() {
        
        //#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10Return_c
        return x10::array::Array<FMGL(T)>::_make(x10aux::max_threads(), t);
        
    }
    
    // captured environment
    FMGL(T) t;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->t);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_WorkerLocalHandle__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_util_WorkerLocalHandle__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_WorkerLocalHandle__closure__0<FMGL(T) > >(storage));
        FMGL(T) that_t = buf.read<FMGL(T)>();
        x10aux::ref<x10_util_WorkerLocalHandle__closure__0<FMGL(T) > > this_ = new (storage) x10_util_WorkerLocalHandle__closure__0<FMGL(T) >(that_t);
        return this_;
    }
    
    x10_util_WorkerLocalHandle__closure__0(FMGL(T) t) : t(t) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10:23";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > >::template itable <x10_util_WorkerLocalHandle__closure__0<FMGL(T) > >x10_util_WorkerLocalHandle__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_WorkerLocalHandle__closure__0<FMGL(T) >::__apply, &x10_util_WorkerLocalHandle__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_WorkerLocalHandle__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > > >, &x10_util_WorkerLocalHandle__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_WorkerLocalHandle__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_WorkerLocalHandle__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_WORKERLOCALHANDLE__CLOSURE__0_CLOSURE
#ifndef X10_UTIL_WORKERLOCALHANDLE_H_GENERICS
#define X10_UTIL_WORKERLOCALHANDLE_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::util::WorkerLocalHandle<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::WorkerLocalHandle<FMGL(T)> >(), 0, sizeof(x10::util::WorkerLocalHandle<FMGL(T)>))) x10::util::WorkerLocalHandle<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_WORKERLOCALHANDLE_H_GENERICS
#ifndef X10_UTIL_WORKERLOCALHANDLE_H_IMPLEMENTATION
#define X10_UTIL_WORKERLOCALHANDLE_H_IMPLEMENTATION
#include <x10/util/WorkerLocalHandle.h>


template<class FMGL(T)> typename x10::lang::Fun_0_0<FMGL(T)>::template itable<x10::util::WorkerLocalHandle<FMGL(T)> >  x10::util::WorkerLocalHandle<FMGL(T)>::_itable_0(&x10::util::WorkerLocalHandle<FMGL(T)>::equals, &x10::util::WorkerLocalHandle<FMGL(T)>::hashCode, &x10::util::WorkerLocalHandle<FMGL(T)>::__apply, &x10::util::WorkerLocalHandle<FMGL(T)>::toString, &x10::util::WorkerLocalHandle<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::util::WorkerLocalHandle<FMGL(T)> >  x10::util::WorkerLocalHandle<FMGL(T)>::_itable_1(&x10::util::WorkerLocalHandle<FMGL(T)>::equals, &x10::util::WorkerLocalHandle<FMGL(T)>::hashCode, &x10::util::WorkerLocalHandle<FMGL(T)>::toString, &x10::util::WorkerLocalHandle<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::VoidFun_0_1<FMGL(T)>::template itable<x10::util::WorkerLocalHandle<FMGL(T)> >  x10::util::WorkerLocalHandle<FMGL(T)>::_itable_2(&x10::util::WorkerLocalHandle<FMGL(T)>::equals, &x10::util::WorkerLocalHandle<FMGL(T)>::hashCode, &x10::util::WorkerLocalHandle<FMGL(T)>::__apply, &x10::util::WorkerLocalHandle<FMGL(T)>::toString, &x10::util::WorkerLocalHandle<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::util::WorkerLocalHandle<FMGL(T)>::_itables[4] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_1<FMGL(T)> >, &_itable_2), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::WorkerLocalHandle<FMGL(T)> >())};
template<class FMGL(T)> void x10::util::WorkerLocalHandle<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::WorkerLocalHandle<FMGL(T)>");
    
}


//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10FieldDecl_c

//#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::WorkerLocalHandle<FMGL(T)>::_constructor(
                          FMGL(T) t) {
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> >)this)->
      FMGL(store) = x10::lang::PlaceLocalHandle_methods<void>::make<x10aux::ref<x10::array::Array<FMGL(T)> > >(
                      x10::array::Dist::makeUnique(),
                      x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > > > >(x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > > >(x10aux::ref<x10_util_WorkerLocalHandle__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10aux::ref<x10::array::Array<FMGL(T)> > > >(sizeof(x10_util_WorkerLocalHandle__closure__0<FMGL(T)>)))x10_util_WorkerLocalHandle__closure__0<FMGL(T)>(t)))));
    
}
template<class FMGL(T)> x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> > x10::util::WorkerLocalHandle<FMGL(T)>::_make(
                          FMGL(T) t) {
    x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::WorkerLocalHandle<FMGL(T)> >(), 0, sizeof(x10::util::WorkerLocalHandle<FMGL(T)>))) x10::util::WorkerLocalHandle<FMGL(T)>();
    this_->_constructor(t);
    return this_;
}



//#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::WorkerLocalHandle<FMGL(T)>::__apply(
  ) {
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::array::Array<FMGL(T)> > >::__apply(((x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> >)this)->
                                                                                                                       FMGL(store)))->x10::array::Array<FMGL(T)>::__apply(
             x10::lang::Runtime::workerId());
    
}

//#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::WorkerLocalHandle<FMGL(T)>::__apply(
  FMGL(T) t) {
    
    //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::array::Array<FMGL(T)> > >::__apply(((x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> >)this)->
                                                                                                                FMGL(store)))->x10::array::Array<FMGL(T)>::__set(
      t,
      x10::lang::Runtime::workerId());
}

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> >
  x10::util::WorkerLocalHandle<FMGL(T)>::x10__util__WorkerLocalHandle____x10__util__WorkerLocalHandle__this(
  ) {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/WorkerLocalHandle.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::WorkerLocalHandle<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::util::WorkerLocalHandle<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::WorkerLocalHandle<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::util::WorkerLocalHandle<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(store));
    
}

template<class FMGL(T)> void x10::util::WorkerLocalHandle<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(store) = buf.read<x10::lang::PlaceLocalHandle<x10aux::ref<x10::array::Array<FMGL(T)> > > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::WorkerLocalHandle<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::WorkerLocalHandle<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::WorkerLocalHandle<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[3] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(), x10aux::getRTT<x10::lang::VoidFun_0_1<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.WorkerLocalHandle";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 3, parents, 1, params, variances);
}
#endif // X10_UTIL_WORKERLOCALHANDLE_H_IMPLEMENTATION
#endif // __X10_UTIL_WORKERLOCALHANDLE_H_NODEPS
