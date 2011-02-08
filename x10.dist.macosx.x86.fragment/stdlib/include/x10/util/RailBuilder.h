#ifndef __X10_UTIL_RAILBUILDER_H
#define __X10_UTIL_RAILBUILDER_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_UTIL_BUILDER_H_NODEPS
#include <x10/util/Builder.h>
#undef X10_UTIL_BUILDER_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class GrowableRail;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 

template<class FMGL(T)> class RailBuilder;
template <> class RailBuilder<void>;
template<class FMGL(T)> class RailBuilder : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::util::Builder<FMGL(T), x10aux::ref<x10::lang::Rail<FMGL(T) > > >::template itable<x10::util::RailBuilder<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::RailBuilder<FMGL(T)> > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::util::GrowableRail<FMGL(T) > > FMGL(buf);
    
    void _constructor();
    
    static x10aux::ref<x10::util::RailBuilder<FMGL(T)> > _make();
    
    void _constructor(x10_int size);
    
    static x10aux::ref<x10::util::RailBuilder<FMGL(T)> > _make(x10_int size);
    
    virtual x10aux::ref<x10::util::Builder<FMGL(T), x10aux::ref<x10::lang::Rail<FMGL(T) > > > >
      add(
      FMGL(T) x);
    virtual x10aux::ref<x10::util::RailBuilder<FMGL(T)> > insert(
      x10_int loc,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > items);
    virtual x10_int length();
    virtual x10aux::ref<x10::lang::Rail<FMGL(T) > > result();
    virtual x10aux::ref<x10::util::RailBuilder<FMGL(T)> > x10__util__RailBuilder____x10__util__RailBuilder__this(
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
template <> class RailBuilder<void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_RAILBUILDER_H

namespace x10 { namespace util { 
template<class FMGL(T)>
class RailBuilder;
} } 

#ifndef X10_UTIL_RAILBUILDER_H_NODEPS
#define X10_UTIL_RAILBUILDER_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/util/Builder.h>
#include <x10/lang/Rail.h>
#include <x10/util/GrowableRail.h>
#include <x10/lang/Int.h>
#ifndef X10_UTIL_RAILBUILDER_H_GENERICS
#define X10_UTIL_RAILBUILDER_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::util::RailBuilder<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::RailBuilder<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::RailBuilder<FMGL(T)> >(), 0, sizeof(x10::util::RailBuilder<FMGL(T)>))) x10::util::RailBuilder<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_RAILBUILDER_H_GENERICS
#ifndef X10_UTIL_RAILBUILDER_H_IMPLEMENTATION
#define X10_UTIL_RAILBUILDER_H_IMPLEMENTATION
#include <x10/util/RailBuilder.h>


template<class FMGL(T)> typename x10::util::Builder<FMGL(T), x10aux::ref<x10::lang::Rail<FMGL(T) > > >::template itable<x10::util::RailBuilder<FMGL(T)> >  x10::util::RailBuilder<FMGL(T)>::_itable_0(&x10::util::RailBuilder<FMGL(T)>::add, &x10::util::RailBuilder<FMGL(T)>::equals, &x10::util::RailBuilder<FMGL(T)>::hashCode, &x10::util::RailBuilder<FMGL(T)>::result, &x10::util::RailBuilder<FMGL(T)>::toString, &x10::util::RailBuilder<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::util::RailBuilder<FMGL(T)> >  x10::util::RailBuilder<FMGL(T)>::_itable_1(&x10::util::RailBuilder<FMGL(T)>::equals, &x10::util::RailBuilder<FMGL(T)>::hashCode, &x10::util::RailBuilder<FMGL(T)>::toString, &x10::util::RailBuilder<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::util::RailBuilder<FMGL(T)>::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::util::Builder<FMGL(T), x10aux::ref<x10::lang::Rail<FMGL(T) > > > >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::RailBuilder<FMGL(T)> >())};
template<class FMGL(T)> void x10::util::RailBuilder<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::RailBuilder<FMGL(T)>");
    
}


//#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10FieldDecl_c

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::RailBuilder<FMGL(T)>::_constructor(
                          ) {
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this)->FMGL(buf) = x10::util::GrowableRail<FMGL(T) >::_make();
    
}
template<class FMGL(T)> x10aux::ref<x10::util::RailBuilder<FMGL(T)> > x10::util::RailBuilder<FMGL(T)>::_make(
                          ) {
    x10aux::ref<x10::util::RailBuilder<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::RailBuilder<FMGL(T)> >(), 0, sizeof(x10::util::RailBuilder<FMGL(T)>))) x10::util::RailBuilder<FMGL(T)>();
    this_->_constructor();
    return this_;
}



//#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::RailBuilder<FMGL(T)>::_constructor(
                          x10_int size) {
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 22 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this)->FMGL(buf) =
      x10::util::GrowableRail<FMGL(T) >::_make(size);
    
}
template<class FMGL(T)> x10aux::ref<x10::util::RailBuilder<FMGL(T)> > x10::util::RailBuilder<FMGL(T)>::_make(
                          x10_int size) {
    x10aux::ref<x10::util::RailBuilder<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::RailBuilder<FMGL(T)> >(), 0, sizeof(x10::util::RailBuilder<FMGL(T)>))) x10::util::RailBuilder<FMGL(T)>();
    this_->_constructor(size);
    return this_;
}



//#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::Builder<FMGL(T), x10aux::ref<x10::lang::Rail<FMGL(T) > > > >
  x10::util::RailBuilder<FMGL(T)>::add(
  FMGL(T) x) {
    
    //#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": polyglot.ast.Eval_c
    (((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this)->
       FMGL(buf))->add(x);
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this);
    
}

//#line 30 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::RailBuilder<FMGL(T)> >
  x10::util::RailBuilder<FMGL(T)>::insert(
  x10_int loc,
  x10aux::ref<x10::lang::Rail<FMGL(T) > > items) {
    
    //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": polyglot.ast.Eval_c
    (((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this)->
       FMGL(buf))->insert(loc, items);
    
    //#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this);
    
}

//#line 35 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::RailBuilder<FMGL(T)>::length(
  ) {
    
    //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this)->
              FMGL(buf))->length();
    
}

//#line 39 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Rail<FMGL(T) > >
  x10::util::RailBuilder<FMGL(T)>::result(
  ) {
    
    //#line 40 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this)->
              FMGL(buf))->toRail();
    
}

//#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::RailBuilder<FMGL(T)> >
  x10::util::RailBuilder<FMGL(T)>::x10__util__RailBuilder____x10__util__RailBuilder__this(
  ) {
    
    //#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/RailBuilder.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::RailBuilder<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::util::RailBuilder<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::RailBuilder<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::util::RailBuilder<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(buf));
    
}

template<class FMGL(T)> void x10::util::RailBuilder<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(buf) = buf.read<x10aux::ref<x10::util::GrowableRail<FMGL(T) > > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::RailBuilder<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::RailBuilder<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::RailBuilder<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::util::Builder<FMGL(T), x10aux::ref<x10::lang::Rail<FMGL(T) > > > >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.RailBuilder";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_RAILBUILDER_H_IMPLEMENTATION
#endif // __X10_UTIL_RAILBUILDER_H_NODEPS
