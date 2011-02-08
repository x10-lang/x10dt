#ifndef __X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H
#define __X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE__ROOTFINISH_H_NODEPS
#include <x10/lang/FinishState__RootFinish.h>
#undef X10_LANG_FINISHSTATE__ROOTFINISH_H_NODEPS
#define X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
#include <x10/lang/FinishState__CollectingFinishState.h>
#undef X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__StatefulReducer;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Reducible;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace lang { 

template<class FMGL(T)> class FinishState__RootCollectingFinish;
template <> class FinishState__RootCollectingFinish<void>;
template<class FMGL(T)> class FinishState__RootCollectingFinish : public x10::lang::FinishState__RootFinish
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[4];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Runtime__Mortal::itable<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > _itable_1;
    
    static typename x10::lang::FinishState__CollectingFinishState<FMGL(T)>::template itable<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > _itable_2;
    
    virtual x10_boolean _isMortal() { return true; }
    
    void _instance_init();
    
    x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >
      FMGL(sr);
    
    void _constructor(x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer);
    
    static x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > _make(
             x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer);
    
    virtual void accept(FMGL(T) t, x10_int id);
    virtual void notifyValue(x10aux::ref<x10::lang::Rail<x10_int > > rail,
                             FMGL(T) v);
    virtual void notifyValue(x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > rail,
                             FMGL(T) v);
    virtual FMGL(T) waitForFinishExpr();
    virtual x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >
      x10__lang__FinishState__RootCollectingFinish____x10__lang__FinishState__RootCollectingFinish__this(
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
template <> class FinishState__RootCollectingFinish<void> : public x10::lang::FinishState__RootFinish
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H

namespace x10 { namespace lang { 
template<class FMGL(T)>
class FinishState__RootCollectingFinish;
} } 

#ifndef X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_NODEPS
#define X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_NODEPS
#include <x10/lang/FinishState__RootFinish.h>
#include <x10/lang/FinishState__CollectingFinishState.h>
#include <x10/lang/FinishState__StatefulReducer.h>
#include <x10/lang/Reducible.h>
#include <x10/lang/Int.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/util/Pair.h>
#ifndef X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_GENERICS
#define X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__RootCollectingFinish<FMGL(T)>))) x10::lang::FinishState__RootCollectingFinish<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_GENERICS
#ifndef X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_IMPLEMENTATION
#define X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_IMPLEMENTATION
#include <x10/lang/FinishState__RootCollectingFinish.h>


template<class FMGL(T)> x10::lang::Runtime__Mortal::itable<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >  x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_itable_0(&x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >  x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_itable_1(&x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::FinishState__CollectingFinishState<FMGL(T)>::template itable<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >  x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_itable_2(&x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::accept, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_itables[4] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Runtime__Mortal>, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >, &_itable_2), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >())};
template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::FinishState__RootCollectingFinish<FMGL(T)>");
    
}


//#line 603 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10FieldDecl_c

//#line 604 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_constructor(
                          x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer)
{
    this->::x10::lang::FinishState__RootFinish::_constructor();
    {
     
    }
    
    //#line 606 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
      FMGL(sr) =
      x10::lang::FinishState__StatefulReducer<FMGL(T)>::_make(reducer);
    
}
template<class FMGL(T)>
x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_make(
  x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer)
{
    x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__RootCollectingFinish<FMGL(T)>))) x10::lang::FinishState__RootCollectingFinish<FMGL(T)>();
    this_->_constructor(reducer);
    return this_;
}



//#line 608 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::accept(
  FMGL(T) t,
  x10_int id) {
    
    //#line 609 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->accept(
      t,
      id);
}

//#line 611 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::notifyValue(
  x10aux::ref<x10::lang::Rail<x10_int > > rail,
  FMGL(T) v) {
    
    //#line 612 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(latch))->lock();
    
    //#line 613 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->accept(
      v);
    
    //#line 614 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->process(
      rail);
    
    //#line 615 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(latch))->unlock();
}

//#line 617 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::notifyValue(
  x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > rail,
  FMGL(T) v) {
    
    //#line 618 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(latch))->lock();
    
    //#line 619 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->accept(
      v);
    
    //#line 620 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->process(
      rail);
    
    //#line 621 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(latch))->unlock();
}

//#line 623 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::waitForFinishExpr(
  ) {
    
    //#line 624 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->waitForFinish();
    
    //#line 625 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->placeMerge();
    
    //#line 626 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
    FMGL(T) result = x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                                         FMGL(sr))->result();
    
    //#line 627 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->reset();
    
    //#line 628 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return result;
    
}

//#line 602 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >
  x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::x10__lang__FinishState__RootCollectingFinish____x10__lang__FinishState__RootCollectingFinish__this(
  ) {
    
    //#line 602 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::FinishState__RootFinish::_serialize_body(buf);
    buf.write(this->FMGL(sr));
    
}

template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::FinishState__RootFinish::_deserialize_body(buf);
    FMGL(sr) = buf.read<x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::FinishState__RootCollectingFinish<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::FinishState__RootFinish>(), x10aux::getRTT<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.FinishState.RootCollectingFinish";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_IMPLEMENTATION
#endif // __X10_LANG_FINISHSTATE__ROOTCOLLECTINGFINISH_H_NODEPS
